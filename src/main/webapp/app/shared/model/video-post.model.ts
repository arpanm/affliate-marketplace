import dayjs from 'dayjs';
import { IVideoTag } from 'app/shared/model/video-tag.model';
import { IVideoUser } from 'app/shared/model/video-user.model';
import { UrlType } from 'app/shared/model/enumerations/url-type.model';

export interface IVideoPost {
  id?: number;
  title?: string | null;
  description?: string | null;
  url?: string | null;
  urlType?: keyof typeof UrlType | null;
  isAIGenerated?: boolean | null;
  isPremium?: boolean | null;
  isBlocked?: boolean | null;
  isModerated?: boolean | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  tags?: IVideoTag[] | null;
  creator?: IVideoUser | null;
}

export const defaultValue: Readonly<IVideoPost> = {
  isAIGenerated: false,
  isPremium: false,
  isBlocked: false,
  isModerated: false,
  isActive: false,
};
