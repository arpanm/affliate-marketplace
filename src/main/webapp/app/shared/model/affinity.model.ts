import dayjs from 'dayjs';
import { IVideoPost } from 'app/shared/model/video-post.model';
import { IVideoUser } from 'app/shared/model/video-user.model';
import { Segment } from 'app/shared/model/enumerations/segment.model';

export interface IAffinity {
  id?: number;
  segment?: keyof typeof Segment | null;
  score?: number | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  posts?: IVideoPost[] | null;
  users?: IVideoUser[] | null;
}

export const defaultValue: Readonly<IAffinity> = {
  isActive: false,
};
