import dayjs from 'dayjs';
import { IVideoPost } from 'app/shared/model/video-post.model';

export interface IVideoTag {
  id?: number;
  name?: string | null;
  code?: string | null;
  isModerated?: boolean | null;
  isDeleted?: boolean | null;
  deletionReason?: string | null;
  mergedWithTagName?: string | null;
  mergedWithTagCode?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  posts?: IVideoPost[] | null;
}

export const defaultValue: Readonly<IVideoTag> = {
  isModerated: false,
  isDeleted: false,
  isActive: false,
};
