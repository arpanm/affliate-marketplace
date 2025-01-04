import dayjs from 'dayjs';
import { IVideoUser } from 'app/shared/model/video-user.model';

export interface IContact {
  id?: number;
  message?: string | null;
  isContactViewed?: boolean | null;
  isFollowed?: boolean | null;
  isDeleted?: boolean | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  sender?: IVideoUser | null;
}

export const defaultValue: Readonly<IContact> = {
  isContactViewed: false,
  isFollowed: false,
  isDeleted: false,
  isActive: false,
};
